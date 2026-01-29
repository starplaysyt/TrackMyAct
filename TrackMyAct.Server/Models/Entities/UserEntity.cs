namespace TrackMyAct.Server.Models.Entities;

public class UserEntity : Entity
{
    public string Username { get; set; }
    public string PasswordHash { get; set; }
    public string Name { get; set; }
}