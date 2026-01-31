using FastEndpoints;
using TrackMyAct.Server.Models.Repositories;
using TrackMyAct.Server.Services;

namespace TrackMyAct.Server.Endpoints.Auth.LoginUser;

public class LoginUserEndpoint : Endpoint<LoginRequest, LoginResponse> 
{
    private readonly UserRepository _userRepository;
    private readonly AuthService _authService;

    public LoginUserEndpoint(UserRepository userRepository, AuthService authService)
    {
        _userRepository = userRepository;
        _authService = authService;
    }

    public override void Configure()
    {
        Post("auth/login");
        AllowAnonymous();
    }

    public override async Task HandleAsync(LoginRequest req, CancellationToken ct)
    {
        var user = await _userRepository.GetByUsername(req.Username);
        
        if(user == null)
            await Send.ResponseAsync(new LoginResponse() { IsSuccess = false, ErrorMessage = $"User with nickname {req.Username} does not exist"});

        await _authService.LoginAsync(req.Username, req.Pathword, HttpContext);
    }
}