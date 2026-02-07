using System.Text.Json.Serialization;

namespace TrackMyAct.Server.Models.Entities;

public class PlaceEntity : Entity
{
    public string Name { get; set; }
    public string Description { get; set; }
    public string Address { get; set; }
    public string AvailableTime { get; set; }

    [JsonIgnore]
    public ICollection<EventEntity> Events { get; set; }
}