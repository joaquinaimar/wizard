using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using Microsoft.Practices.Prism.Commands;
using Microsoft.Practices.Unity;
using Wizard.Model.Service.SysManage;
using Wizard.Wpf.Program.Application.SysManage.ViewEntity;
using Wizard.Wpf.Program.Basic;
using Wizard.Wpf.Program.Basic.Framework;

namespace Wizard.Wpf.Program.Application.SysManage.ViewModel
{
    public class UserManagePageViewModel : ViewModelBase<UserManagePageViewEntity>
    {
        [Dependency]
        public UserManageService userManageService { get; set; }

        #region 修改按钮
        public DelegateCommand BtnModifyCommand
        {
            get
            {
                return new DelegateCommand(this.BtnModifyExecute);
            }
        }

        public void BtnModifyExecute()
        {
            if (this.ViewEntity.NewPassword != this.ViewEntity.ConfirmPassword)
            {
                MessageBox.Show("两次密码输入不一致");
                return;
            }
            string username = SessionContext.GetAttribute<string>("username");
            if (userManageService.ModifyPassword(username, this.ViewEntity.OldPassword, this.ViewEntity.NewPassword))
                MessageBox.Show("修改成功！");
            else
                MessageBox.Show("修改失败！");

        }
        #endregion

    }
}
