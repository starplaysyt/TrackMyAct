using FastEndpoints;
using Microsoft.EntityFrameworkCore;
using TrackMyAct.Server;
using TrackMyAct.Server.Models.Entities;
using TrackMyAct.Server.Models.Repositories;
using TrackMyAct.Server.Services;

public sealed class CreateEventEndpoint: Endpoint<CreateEventRequest>
{
    private readonly AuthService _authService;
    private readonly EventRepository _eventRespository;
    private readonly AppDbContext _appDbContext;

    public CreateEventEndpoint(AuthService authService, EventRepository eventRepository, AppDbContext appDbContext)
    {
        _authService = authService;
        _eventRespository = eventRepository;
        _appDbContext = appDbContext;
    }

    public override void Configure()
    {
        Post("/event/create");
    }

    public override async Task HandleAsync(CreateEventRequest req, CancellationToken ct)
    {
        var currentUserId = await _authService.GetCurrentUserId();

        var organizer = await _appDbContext.Users.OfType<OrganizerEntity>().FirstOrDefaultAsync(x => x.Id == currentUserId);

        EventEntity newEvent = new()
        {
              Name = req.Name,
              Description = req.Description,
              StartDateTime = req.StartDate.Date + req.StartTime.TimeOfDay,
              EventPlace = req.EventPlace,
              Organizer = organizer,
              OrganizerId = organizer.Id
        };

        await _eventRespository.Create(newEvent);
        await Send.OkAsync();
    }
}