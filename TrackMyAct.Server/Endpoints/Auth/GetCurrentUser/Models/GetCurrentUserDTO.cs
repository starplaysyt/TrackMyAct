using System.ComponentModel.DataAnnotations;

namespace TrackMyAct.Server.Endpoints.Auth.GetCurrentUser.Models;

public class GetCurrentUserDTO
{
    public required string Username { get; set; }

    public required string Email { get; set; }

    public required string Name { get; set; }

    public required string RoleType { get; set; } // Participant/Orginizer
}