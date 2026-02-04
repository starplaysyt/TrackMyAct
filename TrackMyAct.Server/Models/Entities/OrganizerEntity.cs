namespace TrackMyAct.Server.Models.Entities;

public class OrganizerEntity : UserEntity
{
    public string Organization { get; set; }
    public string Key { get; set; }
    public ICollection<EventEntity> OrganizedEvents { get; set; }
    public ICollection<EventEntity> ArchiveEvents { get; set; }
    public ICollection<ParticipantEntity> Subscribers { get; set; }
    public ICollection<CommentEntity> Comments { get; set; }
}