public class ParticipiantEntity : UserEntity
{
    public DateTime BirthdayDate { get; set; }
    public string Phone { get; set; }
    public string Email { get; set; }
    public bool IsVerifed { get; set; }
    public ICollection<InterestEntity> Interests { get; set; }
    public ICollection<EventEntity> Participations { get; set; }
    public ICollection<CommentEntity> Comments { get; set; }
    public ICollection<EventEntity> ArchiveEvents { get; set; }
    public ICollection<EventEntity> ParticipantRequests { get; set; }
    public ICollection<EventEntity> OrganizerRequests { get; set; }
}