using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using Wizard.Wpf.Program.Application.SysManage.ViewModel;
using Wizard.Wpf.Program.Basic.Framework.Expand;

namespace Wizard.Wpf.Program.Application.SysManage.View
{
    /// <summary>
    /// UserManagePageView.xaml 的交互逻辑
    /// </summary>
    public partial class UserManagePageView : PageExpand
    {
        public UserManagePageView(UserManagePageViewModel model)
            : base(model)
        {
            InitializeComponent();
        }
    }
}
