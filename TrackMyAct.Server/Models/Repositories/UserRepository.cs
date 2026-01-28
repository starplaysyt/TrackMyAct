using Microsoft.EntityFrameworkCore;

public class UserRepository
{
    private readonly AppDbContext _dbContext;

    public UserRepository(AppDbContext dbContext)
    {
        _dbContext = dbContext;
    }

    public async Task<UserEntity> GetByUsername(string username) =>
        await _dbContext.Users.FirstOrDefaultAsync(x => x.Username == username);

    public async Task Change(UserEntity user)
    {
        _dbContext.Users.Update(user);
        await _dbContext.SaveChangesAsync();
    }

    public async Task Create(UserEntity user)
    {
        await _dbContext.Users.AddAsync(user);
        await _dbContext.SaveChangesAsync();
    }

    public async Task DeleteById(int id)
    {
        var user = await _dbContext.Users.FirstOrDefaultAsync(x => x.Id == id);

        _dbContext.Users.Remove(user);
    }

    public async Task<UserEntity> GetById(int id) =>
        await _dbContext.Users.FirstOrDefaultAsync(x => x.Id == id);

    public async Task<List<UserEntity>> GetList() =>
        await _dbContext.Users.ToListAsync();
}

