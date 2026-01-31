using FastEndpoints;
using TrackMyAct.Server.Services;

namespace TrackMyAct.Server.Endpoints.Auth.RegisterUser;

public class RegisterUserEndpoint : Endpoint<RegistrationRequest, RegistrationResponse>
{
    private readonly AuthService _authService;

    public RegisterUserEndpoint(AuthService authService)
    {
        _authService = authService;
    }

    public override void Configure()
    {
        Post("/auth/register");
        AllowAnonymous();
    }

    public override async Task HandleAsync(RegistrationRequest req, CancellationToken ct)
    {
        try
        {
            await _authService.RegisterAsync(req.Login, req.Email, req.Password, req.Name, req.Phone, req.BirthDate);
            await Send.OkAsync(new RegistrationResponse { IsSuccess = true });
        }
        catch(ArgumentException ex)
        {
            await Send.ResponseAsync(new RegistrationResponse { IsSuccess = false, ErrorMessage = ex.Message});
        }
        catch(Exception)
        {
            await Send.StringAsync("Error while register", 500);
        }
    }
}