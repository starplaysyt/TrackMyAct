namespace TrackMyAct.Server.Endpoints.Auth.Models;

public class AuthResponse
{
    public bool IsSuccess { get; set; }
    public string Error { get; set; } = String.Empty;
}