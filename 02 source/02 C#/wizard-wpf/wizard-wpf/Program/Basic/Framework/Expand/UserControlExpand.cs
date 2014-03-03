using System.Windows.Controls;
using Wizard.Wpf.Program.Helper;

namespace Wizard.Wpf.Program.Basic.Framework.Expand
{
    public class UserControlExpand : UserControl
    {
        public UserControlExpand(object model)
        {
            CommonHelper.SetPropertyValue(model, "View", this);
            this.DataContext = model;
        }
    }
}
