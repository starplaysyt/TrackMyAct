namespace TrackMyAct.Server.Models.Entities;

public class InterestEntity : Entity
{
    public string Name { get; set; }
    public string Description { get; set; }
    public ICollection<ParticipantEntity> Participants { get; set; }
    public ICollection<EventEntity> Events { get; set; }
}