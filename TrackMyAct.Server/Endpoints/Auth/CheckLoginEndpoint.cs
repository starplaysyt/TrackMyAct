using System.Net.Mail;
using FastEndpoints;

namespace TrackMyAct.Server.Endpoints.Auth;

public class CheckLoginEndpoint : EndpointWithoutRequest<AuthResponse>
{
    public override void Configure()
    {
        Get("/user/checkLogIn");
    }

    public override async Task HandleAsync(CancellationToken ct)
    {
        await Send.OkAsync(new () { IsSuccess = true, Message = null });
    }
}