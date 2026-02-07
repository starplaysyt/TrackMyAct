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

    public async Task RegisterOrganizerAsync(string username, string email, string password, string name, string organization, string key)
    {
        var isExisting = await _userRepository.GetByUsername(username) != null;

        if(isExisting)
           throw new Exception("Organizer is already exist!");

        var passwordHash = _hasher.CreatePasswordHash(password);

        var organizer = new OrganizerEntity
        {
            RoleType = "Organizer",
            Username = username,
            Name = name,
            PasswordHash = passwordHash,
            Email = email,
            Organization = organization,
            Key = key
        };

        await _userRepository.Create(organizer);
    }

    public async Task RegisterParticipantAsync(string username, string password, string name, DateTime birthDate, string phone, string email)
    {
        var isExisting = await _userRepository.GetByUsername(username) != null;

        if(isExisting)
            throw new Exception("Participant is already exist");

        var passwordHash = _hasher.CreatePasswordHash(password);

        var participant = new ParticipantEntity
        {
            RoleType = "Participant",
            Username = username,
            Email = email,
            Name = name,
            PasswordHash = passwordHash,
            BirthdayDate = birthDate,
            Phone = phone,
            IsVerifed = false  
        };

        await _userRepository.Create(participant);
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
            new (ClaimTypes.Role, user.RoleType)
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