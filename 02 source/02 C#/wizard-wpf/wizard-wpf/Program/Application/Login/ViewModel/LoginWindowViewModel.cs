using System.Windows;
using Microsoft.Practices.Prism.Commands;
using Microsoft.Practices.Unity;
using Wizard.Model.Service.Login;
using Wizard.Wpf.Program.Application.Login.View;
using Wizard.Wpf.Program.Application.Login.ViewEntity;
using Wizard.Wpf.Program.Basic;
using Wizard.Wpf.Program.Basic.Framework;
using Wizard.Wpf.Program.Helper;

namespace Wizard.Wpf.Program.Application.Login.ViewModel
{
    public class LoginWindowViewModel : ViewModelBase<LoginWindowViewEntity>
    {

        [Dependency]
        public LoginService loginService { get; set; }

        #region 画面加载完
        public DelegateCommand LoadedCommand
        {
            get
            {
                return new DelegateCommand(this.LoadedExecute);
            }
        }

        public void LoadedExecute()
        {
            this.ViewEntity.Username = "zhanglizhi";
            this.ViewEntity.Password = "042888";
        }
        #endregion

        #region 登录按钮
        public DelegateCommand BtnLoginCommand
        {
            get
            {
                return new DelegateCommand(this.BtnLoginExecute);
            }
        }

        public void BtnLoginExecute()
        {
            if (this.loginService.Login(this.ViewEntity.Username, this.ViewEntity.Password))
            {
                SessionContext.SetAttribute("username", this.ViewEntity.Username);
                UnityHelper.GetInstance<Wizard.Wpf.Program.Application.Main.View.MainWindowView>().Show();
                this.GetView<LoginWindowView>().Close();
            }
            else
            {
                MessageBox.Show("用户名或密码不正确！");
            }
        }
        #endregion

        #region 重置按钮
        public DelegateCommand BtnResetCommand
        {
            get
            {
                return new DelegateCommand(this.BtnResetExecute);
            }
        }

        public void BtnResetExecute()
        {
            this.ViewEntity.Username = "";
            this.ViewEntity.Password = "";

        }
        #endregion

    }
}
