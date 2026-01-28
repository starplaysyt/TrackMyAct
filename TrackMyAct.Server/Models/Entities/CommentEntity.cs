public class CommentEntity : Entity
{
    public string Text { get; set; }
    public CommentType Type { get; set; }

    public int Mark { get; set; }

    public ParticipantEntity Author {get; set; }
    public int AuthorId { get; set; }

    public EventEntity Event { get; set; }
    public int? EventId { get; set; }

    public OrganizerEntity Organizer { get; set; }
    public int? OrganizerId { get; set; }
}
