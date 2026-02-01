using FastEndpoints;
using TrackMyAct.Server.Services;

namespace TrackMyAct.Server.Endpoints.Auth.LogoutUser;

public class LogoutEndpoint : EndpointWithoutRequest
{
    private readonly AuthService _authService;

    public LogoutEndpoint(AuthService authService)
    {
        _authService = authService;
    }

    public override void Configure()
    {
        Post("/auth/logout");
    }

    public override async Task HandleAsync(CancellationToken ct)
    {
        await _authService.LogoutAsync(HttpContext);
    }
}