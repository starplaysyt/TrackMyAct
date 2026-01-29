using System.ComponentModel.DataAnnotations;

namespace TrackMyAct.Server.Endpoints.Auth.Models;

public class AuthRequest
{
    [Required, MinLength(3)]
    public string Login { get; set; }
    [Required, MinLength(6)]
    public string Password { get; set; }
}