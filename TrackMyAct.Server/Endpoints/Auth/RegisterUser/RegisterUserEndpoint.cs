using FastEndpoints;

public class RegisterUserEndpoint : Endpoint<AuthRequest, AuthResponse>
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

    public override async Task HandleAsync(AuthRequest req, CancellationToken ct)
    {
        try
        {
            await _authService.RegisterAsync(req.Login, req.Password);
            await Send.OkAsync(new AuthResponse { IsSuccess = true });
        }
        catch(ArgumentException ex)
        {
            await Send.ResponseAsync(new AuthResponse { IsSuccess = false, Error = ex.Message});
        }
        catch(Exception)
        {
            await Send.StringAsync("Error while register", 500);
        }
    }
}