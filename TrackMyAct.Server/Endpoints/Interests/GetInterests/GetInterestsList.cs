using System.Runtime.CompilerServices;
using FastEndpoints;
using TrackMyAct.Server.Models.Repositories;

namespace TrackMyAct.Server.Endpoints.Interests;

public sealed class GetInterestsListEndpoint: EndpointWithoutRequest
{
    private readonly InterestRepository _interestRepository;

    public GetInterestsListEndpoint(InterestRepository interestRepository)
    {
        _interestRepository = interestRepository;
    }

    public override void Configure()
    {
        Post("/interest/list");
    }

    public override async Task HandleAsync(CancellationToken ct)
    {
        var result = await _interestRepository.GetList();

        await Send.OkAsync(result);
    }
}