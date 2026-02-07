using FastEndpoints;
using TrackMyAct.Server.Models.Repositories;

namespace TrackMyAct.Server.Endpoints.Events.SendInvite;

public sealed class SendInviteEndpoint : Endpoint<SendInviteRequest>
{
    private readonly UserRepository _userRepository;
 
    public SendInviteEndpoint(UserRepository userRepository)
    {
        _userRepository = userRepository;
    }

    public override void Configure()
    {
        Post("/event/invite");
    }

    public override async Task HandleAsync(SendInviteRequest req, CancellationToken ct)
    {
        
    }
}