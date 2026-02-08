using FastEndpoints;
using Microsoft.AspNetCore.Authorization;
using TrackMyAct.Server.Models.Entities;
using TrackMyAct.Server.Models.Repositories;

namespace TrackMyAct.Server.Endpoints.Events.GetEvents;

public class GetEventsListEndpoint : EndpointWithoutRequest<List<EventEntity>>
{
    private readonly EventRepository _eventRepository;

    public GetEventsListEndpoint(EventRepository eventRepository)
    {
        _eventRepository = eventRepository;
    }

    public override void Configure()
    {
        Get("/event/list");
    }

    public override async Task HandleAsync(CancellationToken ct)
    {
        var result = (await _eventRepository.GetList()).ToList();

        await Send.OkAsync(result);
    }
}