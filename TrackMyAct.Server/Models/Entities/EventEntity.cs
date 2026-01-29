namespace TrackMyAct.Server.Models.Entities;

public class EventEntity : Entity
{
    public string Name { get; set; }
    public string Description { get; set; }
    public DateTime StartDateTime { get; set; }
    public bool NeedAcception { get; set; }

    public PlaceEntity EventPlace { get; set; }
    public int EventPlaceId { get; set; }

    public OrganizerEntity Organizer { get; set; }
    public int OrganizerId { get; set; }
    public ICollection<ParticipantEntity> AcceptedParticipants { get; set; }

    public ICollection<ParticipantEntity> ParticipantRequests { get; set; }
    public ICollection<ParticipantEntity> OrganizerRequests { get; set; }
}