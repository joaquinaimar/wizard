using Wizard.Wpf.Program.Application.Login.ViewModel;
using Wizard.Wpf.Program.Basic.Framework.Expand;

namespace Wizard.Wpf.Program.Application.Login.View
{
    /// <summary>
    /// LoginWindowView.xaml 的交互逻辑
    /// </summary>
    public partial class LoginWindowView : WindowExpand
    {

        public LoginWindowView(LoginWindowViewModel model)
            : base(model)
        {
            InitializeComponent();
        }
    }
}
