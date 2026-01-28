using Microsoft.EntityFrameworkCore;

public class AppDbContext : DbContext
{
    public DbSet<UserEntity> Users { get; set; }
    public DbSet<PlaceEntity> Places { get; set; }
    public DbSet<CommentEntity> Comments { get; set; }
    public DbSet<EventEntity> Events { get; set; }
    public DbSet<InterestEntity> Interests { get; set; }

    public AppDbContext(DbContextOptions<AppDbContext> options) : base(options) { }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<UserEntity>(entity =>
        {
            entity.HasDiscriminator<string>("UserType")
                  .HasValue<ParticipantEntity>("Participant")
                  .HasValue<OrganizerEntity>("Organizer");
        });

        modelBuilder.Entity<ParticipantEntity>(entity =>
        {
            entity.HasMany(x => x.Interests).WithMany(x => x.Participants).UsingEntity(j => j.ToTable("ParticipantInterests"));
            entity.HasMany(x => x.Participations).WithMany(x => x.AcceptedParticipants).UsingEntity(j => j.ToTable("EventParticipants"));
            entity.HasMany(x => x.ArchiveEvents).WithMany().UsingEntity(j => j.ToTable("ParticipantArchiveEvents"));
            entity.HasMany(x => x.ParticipantRequests).WithMany(x => x.ParticipantRequests).UsingEntity(j => j.ToTable("EventParticipantRequests"));
            entity.HasMany(x => x.OrganizerRequests).WithMany(x => x.OrganizerRequests).UsingEntity(j => j.ToTable("EventOrganizerRequests"));
            entity.HasMany(x => x.OrganizerSubscriptions).WithMany(x => x.Subscribers).UsingEntity(j => j.ToTable("OrganizerSubscriptions"));
        });

        modelBuilder.Entity<OrganizerEntity>(entity =>
        {
            entity.HasMany(x => x.OrganizedEvents).WithOne(x => x.Organizer).HasForeignKey(x => x.OrganizerId).OnDelete(DeleteBehavior.Cascade);
        });

        modelBuilder.Entity<CommentEntity>(entity =>
        {
            entity.HasOne(c => c.Author).WithMany(p => p.Comments).HasForeignKey(c => c.AuthorId).OnDelete(DeleteBehavior.NoAction);
            entity.HasOne(c => c.Event).WithMany().HasForeignKey(c => c.EventId).OnDelete(DeleteBehavior.Cascade);
            entity.HasOne(c => c.Organizer).WithMany(o => o.Comments).HasForeignKey(c => c.OrganizerId).OnDelete(DeleteBehavior.Cascade);
        });

        base.OnModelCreating(modelBuilder);
    }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
    {
        base.OnConfiguring(optionsBuilder);
    }
}
