using Wizard.Wpf.Program.Basic.Framework;

namespace Wizard.Wpf.Program.Application.Login.ViewEntity
{
    public class LoginWindowViewEntity : ViewEntityBase
    {
        private string username;
        public string Username
        {
            get
            {
                return username;
            }
            set
            {
                username = value;
                RaisePropertyChanged("Username");
            }
        }

        private string password;
        public string Password
        {
            get
            {
                return password;
            }
            set
            {
                password = value;
                RaisePropertyChanged("Password");
            }
        }
    }
}
