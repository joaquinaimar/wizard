using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Wizard.Wpf.Program.Basic.Framework;

namespace Wizard.Wpf.Program.Application.SysManage.ViewEntity
{
    public class UserManagePageViewEntity : ViewEntityBase
    {
        private string oldPassword;
        public string OldPassword
        {
            get
            {
                return oldPassword;
            }
            set
            {
                oldPassword = value;
                RaisePropertyChanged("OldPassword");
            }
        }

        private string newPassword;
        public string NewPassword
        {
            get
            {
                return newPassword;
            }
            set
            {
                newPassword = value;
                RaisePropertyChanged("NewPassword");
            }
        }

        private string confirmPassword;
        public string ConfirmPassword
        {
            get
            {
                return confirmPassword;
            }
            set
            {
                confirmPassword = value;
                RaisePropertyChanged("ConfirmPassword");
            }
        }
    }
}
