using FastEndpoints;
using TrackMyAct.Server.Services;

namespace TrackMyAct.Server.Endpoints.Auth.RegisterUser.RegisterPaticipant;

public class RegisterParticipantEndpoint : Endpoint<RegisterParticipantRequest, AuthResponse>
{
    private readonly AuthService _authService;

    public RegisterParticipantEndpoint(AuthService authService)
    {
        _authService = authService;
    }

    public override void Configure()
    {
        Post("/user/particiant/register");
        AllowAnonymous();
    }

    public override async Task HandleAsync(RegisterParticipantRequest req, CancellationToken ct)
    {
        try
        {
            await _authService.RegisterParticipantAsync(req.Login, req.Password, req.Name, req.BirthDate, req.Phone, req.Email);            
            await Send.OkAsync(new () { IsSuccess = true, Message = null });
        }
        catch(Exception ex)
        {
            await Send.ResponseAsync(new () { IsSuccess = false, Message = ex.Message });
        }
    }
}