using FastEndpoints;
using TrackMyAct.Server.Endpoints.Auth.GetCurrentUser.Models;
using TrackMyAct.Server.Services;

namespace TrackMyAct.Server.Endpoints.Auth.GetCurrentUser;

public class GetCurrentUserEndpoint : EndpointWithoutRequest<GetCurrentUserDTO>
{
    private readonly AuthService _authService;

    public GetCurrentUserEndpoint(AuthService authService)
    {
        _authService = authService;
    }
    
    public override void Configure()
    {
        Get("/user/getCurrentUser");
    }

    public override async Task HandleAsync(CancellationToken ct)
    {
        var user = await _authService.GetCurrentUser();

        if (user is null)
        {
            await Send.NotFoundAsync(ct);
            return;
        }
        
        var userDto = new GetCurrentUserDTO()
        {
            Name = user.Name,
            Email = user.Email,
            RoleType = user.RoleType,
            Username = user.Username,
        };
        
        await Send.OkAsync(userDto, ct);
    }
}