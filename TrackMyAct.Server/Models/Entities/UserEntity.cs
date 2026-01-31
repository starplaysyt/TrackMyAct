namespace TrackMyAct.Server.Models.Entities;

public class UserEntity : Entity
{
    public string Username { get; set; }
    public string Email { get; set; }
    public string PasswordHash { get; set; }
    public string Name { get; set; }
    public string Phone { get; set; }
    public DateTime BirthDate { get; set; }
}