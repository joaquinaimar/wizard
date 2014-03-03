using System.Linq;
using Wizard.Model.Entity;

namespace Wizard.Model.Service.Login
{
    public class LoginService : ServiceBase
    {
        public bool Login(string username, string password)
        {
            WizardUserInfo userInfo = this.dbContext.WizardUserInfo.SingleOrDefault(entity =>
                (entity.Username == username && entity.Password == password)
            );
            return (null != userInfo);
        }

    }
}
