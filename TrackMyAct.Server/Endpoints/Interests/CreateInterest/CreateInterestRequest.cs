using System.ComponentModel.DataAnnotations;

public class CreateInterestRequest
{
    [Required]
    public string Name { get; set; } = string.Empty;

    [Required]
    public string Description { get; set; } = string.Empty;
}