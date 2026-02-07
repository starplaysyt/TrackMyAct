using System.ComponentModel.DataAnnotations;
using TrackMyAct.Server.Models.Entities;

public class CreateEventRequest
{
    [Required]
    public string Name { get; set; } = string.Empty;

    public DateTime StartDate { get; set; }
    public DateTime StartTime { get; set; }
    
    [Required]
    public PlaceEntity EventPlace { get; set; } = new();

    [Required]
    public string Description { get; set; } = string.Empty;

    public bool IsAdult { get; set; }
}