using Wizard.Wpf.Program.Application.Main.ViewModel;
using Wizard.Wpf.Program.Basic.Framework.Expand;

namespace Wizard.Wpf.Program.Application.Main.View
{
    /// <summary>
    /// MainWindowView.xaml 的交互逻辑
    /// </summary>
    public partial class MainWindowView : WindowExpand
    {
        public MainWindowView(MainWindowViewModel model)
            : base(model)
        {
            InitializeComponent();
        }
    }
}
