using Microsoft.EntityFrameworkCore;
using TrackMyAct.Server.Models.Entities;

namespace TrackMyAct.Server.Models.Repositories;

public class EventRepository
{
    private readonly AppDbContext _appDbContext;

    public EventRepository(AppDbContext appDbContext)
    {
        _appDbContext = appDbContext;
    }

    public async Task<IReadOnlyList<EventEntity>> GetList() =>
        await _appDbContext.Events.AsNoTracking().ToListAsync();

    public async Task Create(EventEntity entity)
    {
        await _appDbContext.AddAsync(entity);
        await _appDbContext.SaveChangesAsync();
    }
}