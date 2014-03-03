using System.Windows.Controls;
using Wizard.Wpf.Program.Helper;

namespace Wizard.Wpf.Program.Basic.Framework.Expand
{
    public class PageExpand : Page
    {
        public PageExpand(object model)
        {
            CommonHelper.SetPropertyValue(model, "View", this);
            this.DataContext = model;
        }
    }
}
