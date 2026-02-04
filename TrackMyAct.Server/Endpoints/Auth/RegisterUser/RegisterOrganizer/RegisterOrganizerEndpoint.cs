using FastEndpoints;
using TrackMyAct.Server.Services;

namespace TrackMyAct.Server.Endpoints.Auth.RegisterUser.RegisterOrganizer;

public class RegisterOrganizerEndpoint : Endpoint<RegisterOrganizerRequest, AuthResponse>
{
    private readonly AuthService _authService;

    public RegisterOrganizerEndpoint(AuthService authService)
    {
        _authService = authService;
    }

    public override void Configure()
    {
        Post("/user/organizer/register");
        AllowAnonymous();
    }

    public override async Task HandleAsync(RegisterOrganizerRequest req, CancellationToken ct)
    {
        try
        {
            await _authService.RegisterOrganizerAsync(req.Login, req.Email, req.Password, req.Name, req.Organization);
            await Send.OkAsync(new () { IsSuccess = true, Message = null });
        }
        catch(Exception ex)
        {
            await Send.ResponseAsync(new () { IsSuccess = false, Message = ex.Message });
        }
    }
}