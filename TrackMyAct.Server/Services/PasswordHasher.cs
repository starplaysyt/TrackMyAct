using System.Security.Cryptography;
using Isopoh.Cryptography.Argon2;

public class PasswordHasher
{
    public string CreatePasswordHash(string password)
    {
        var config = new Argon2Config
        {
            Type = Argon2Type.HybridAddressing,
            Version = Argon2Version.Nineteen,
            Password = System.Text.Encoding.UTF8.GetBytes(password),
            Salt = RandomNumberGenerator.GetBytes(16),
            TimeCost = 4,
            MemoryCost = 1024 * 64,
            Lanes = Environment.ProcessorCount,
            Threads = Environment.ProcessorCount
        };

        var passwordHash = Argon2.Hash(config);

        return passwordHash;
    }

    public bool VerifyPassword(string hashedPassword, string providedPassword)
    {
        return Argon2.Verify(hashedPassword, providedPassword);
    }

}
