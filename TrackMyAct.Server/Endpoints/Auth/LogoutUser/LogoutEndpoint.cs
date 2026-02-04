using FastEndpoints;
using TrackMyAct.Server.Services;

namespace TrackMyAct.Server.Endpoints.Auth.LogoutUser;

public class LogoutEndpoint : EndpointWithoutRequest<AuthResponse>
{
    private readonly AuthService _authService;

    public LogoutEndpoint(AuthService authService)
    {
        _authService = authService;
    }

    public override void Configure()
    {
        Post("/user/logout");
    }

    public override async Task HandleAsync(CancellationToken ct)
    {
        try
        {
            await _authService.LogoutAsync(HttpContext);
            await Send.OkAsync(new () { IsSuccess = true, Message = null });   
        }
        catch(Exception ex)
        {
            await Send.ResponseAsync(new () { IsSuccess = false, Message = ex.Message });
        }
    }
}