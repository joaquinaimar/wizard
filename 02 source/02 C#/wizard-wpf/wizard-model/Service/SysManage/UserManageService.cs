using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Wizard.Model.Entity;

namespace Wizard.Model.Service.SysManage
{
    public class UserManageService : ServiceBase
    {
        public bool ModifyPassword(string username, string password, string newPassword)
        {

            var result = from u in dbContext.WizardUserInfo
                         where u.Username == username && u.Password == password
                         select u;

            var target = result.SingleOrDefault<WizardUserInfo>();

            if (null != target)
            {
                target.Password = newPassword;
                return 1 == this.dbContext.SaveChanges();
            }
            else
            {
                return false;
            }
        }
    }
}
