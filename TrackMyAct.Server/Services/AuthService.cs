using System.Security.Claims;
using Microsoft.AspNetCore.Authentication;
using TrackMyAct.Server.Models.Entities;
using TrackMyAct.Server.Models.Repositories;

namespace TrackMyAct.Server.Services;

public class AuthService
{
    private readonly UserRepository _userRepository;
    private readonly PasswordHasher _hasher;
    private readonly IHttpContextAccessor _httpContextAccessor;

    public AuthService(
        UserRepository userRepository,
        PasswordHasher hasher,
        IHttpContextAccessor httpContextAccessor)
    {
        _userRepository = userRepository;
        _hasher = hasher;
        _httpContextAccessor = httpContextAccessor;
    }

    public async Task RegisterAsync(string username, string email, string password, string name, string phone, DateTime birthday)
    {
        var isExisting = await _userRepository.GetByUsername(username) != null;

        if (isExisting)
            throw new Exception("Username is already exist!");

        var passwordHash = _hasher.CreatePasswordHash(password);

        var user = new UserEntity
        {
            Username = username,
            Name = name,
            PasswordHash = passwordHash,
            Email = email,
            Phone = phone, 
            BirthDate = birthday
        };

        await _userRepository.Create(user);
    }

    public async Task<bool> LoginAsync(string username, string password, HttpContext context)
    {
        var user = await _userRepository.GetByUsername(username);
        if (user is null) 
            return false;

        if (!_hasher.VerifyPassword(user.PasswordHash, password))
            return false;

        var claims = new List<Claim>
        {
            new (ClaimTypes.NameIdentifier, user.Id.ToString()),
            new (ClaimTypes.Name, user.Username),
        };

        var identity = new ClaimsIdentity(claims, "Cookies");
        var principal = new ClaimsPrincipal(identity);

        await context.SignInAsync("Cookies", principal);

        return true;
    }

    public async Task LogoutAsync(HttpContext contexts)
    {
        await contexts.SignOutAsync("Cookies");
    }

    public async Task<UserEntity?> GetCurrentUser()
    {
        var user = _httpContextAccessor.HttpContext?.User;
        if (user?.Identity is null || !user.Identity.IsAuthenticated)
            throw new Exception("UserEntity is not authenticated");

        var userIdClaim = user.FindFirst(ClaimTypes.NameIdentifier);
        if (userIdClaim == null)
            throw new Exception("UserEntity ID claim not found");

        if (!int.TryParse(userIdClaim.Value, out var userId))
            throw new Exception($"Invalid user id claim: {userIdClaim.Value}");

        return await _userRepository.GetById(userId);
    }

    public async Task<int?> GetCurrentUserId()
    {
        var user = _httpContextAccessor.HttpContext?.User;
        
        if (user?.Identity is null || !user.Identity.IsAuthenticated)
            throw new Exception("UserEntity is not authenticated");

        var userIdClaim = user.FindFirst(ClaimTypes.NameIdentifier);
        if (userIdClaim == null)
            throw new Exception("UserEntity ID claim not found");

        if (!int.TryParse(userIdClaim.Value, out var userId))
            throw new Exception($"Invalid user id claim: {userIdClaim.Value}");

        return userId;
    }
}