using FastEndpoints;
using FastEndpoints.Swagger;
using Microsoft.EntityFrameworkCore;
using TrackMyAct.Server;
using TrackMyAct.Server.Models.Repositories;
using TrackMyAct.Server.Services;

var builder = WebApplication.CreateBuilder(args);
builder.Services.AddFastEndpoints()
                .SwaggerDocument();

builder.Services.AddDbContext<AppDbContext>(options =>
    options.UseSqlite(builder.Configuration.GetConnectionString("DefaultConnection")));

builder.Services.AddAuthentication("Cookies").AddCookie("Cookies");
builder.Services.AddAuthorization();

builder.Services.AddHttpContextAccessor();

//Repos
builder.Services.AddScoped<UserRepository>();
builder.Services.AddScoped<EventRepository>();
builder.Services.AddScoped<InterestRepository>();

//Services
builder.Services.AddScoped<AuthService>();
builder.Services.AddScoped<PasswordHasher>();

var app = builder.Build();


app.UseAuthentication();
app.UseAuthorization();

app.UseFastEndpoints()
    .UseSwaggerGen();

app.Run();
