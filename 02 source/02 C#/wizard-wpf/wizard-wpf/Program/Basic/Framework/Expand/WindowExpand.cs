
using System.Windows;
using Wizard.Wpf.Program.Helper;
namespace Wizard.Wpf.Program.Basic.Framework.Expand
{
    public class WindowExpand : Window
    {
        public WindowExpand(object model)
        {
            CommonHelper.SetPropertyValue(model, "View", this);
            this.DataContext = model;
        }

    }
}
