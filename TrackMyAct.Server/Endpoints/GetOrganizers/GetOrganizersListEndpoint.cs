using FastEndpoints;
using TrackMyAct.Server.Models.Repositories;

namespace TrackMyAct.Server.Endpoints.GetOrganizers;

public class GetOrganizersListEndpoint : EndpointWithoutRequest<GetOrganizersListResponse>
{
    private readonly UserRepository _userRepository;

    public GetOrganizersListEndpoint(UserRepository userRepository)
    {
        _userRepository = userRepository;
    }

    public override void Configure()
    {
        Get("organizer/list");   
    }

    public override async Task HandleAsync(CancellationToken ct)
    {
        try
        {
            var organizers = await _userRepository.GetOrganizersList();
            await Send.OkAsync(new () { Organizers = organizers });
        }
        catch
        {
            await Send.ErrorsAsync();
        }
    }
}