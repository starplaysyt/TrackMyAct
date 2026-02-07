using TrackMyAct.Server.Models.Entities;

public class GetOrganizersListResponse
{
    public IReadOnlyList<OrganizerEntity> Organizers { get; set; }
}