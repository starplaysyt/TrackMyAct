using System.ComponentModel.DataAnnotations;

public class AuthRequest
{
    [Required, MinLength(3)]
    public string Login { get; set; }
    [Required, MinLength(6)]
    public string Password { get; set; }
}