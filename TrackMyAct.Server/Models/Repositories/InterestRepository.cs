using Microsoft.EntityFrameworkCore;
using TrackMyAct.Server.Models.Entities;

namespace TrackMyAct.Server.Models.Repositories;

public class InterestRepository
{
    private readonly AppDbContext _appDbContext;

    public InterestRepository(AppDbContext appDbContext)
    {
        _appDbContext = appDbContext;
    }

    public async Task<IReadOnlyList<InterestEntity>> GetList() =>
        await _appDbContext.Interests.AsNoTracking().ToListAsync();

    public async Task Create(InterestEntity entity)
    {
        await _appDbContext.AddAsync(entity);
        await _appDbContext.SaveChangesAsync();
    }
}