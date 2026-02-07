using FastEndpoints;
using TrackMyAct.Server.Models.Entities;
using TrackMyAct.Server.Models.Repositories;

namespace TrackMyAct.Server.Endpoints.Interests.CreateInterest;

public sealed class CreateInterestEndpoint: Endpoint<CreateInterestRequest>
{
    private readonly InterestRepository _interestRepository;

    public CreateInterestEndpoint(InterestRepository interestRepository)
    {
        _interestRepository = interestRepository;
    }

    public override void Configure()
    {
        Post("/interest/create");
    }

    public override async Task HandleAsync(CreateInterestRequest req, CancellationToken ct)
    {
        InterestEntity newInterest = new()
        {
            Name = req.Name,
            Description = req.Description
        };

        await _interestRepository.Create(newInterest);
        await Send.OkAsync();
    }
}