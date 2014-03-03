using System.Windows;
using Microsoft.Practices.Prism.UnityExtensions;
using Wizard.Wpf.Program.Helper;

namespace Wizard.Wpf
{
    public class AppBootStrapper : UnityBootstrapper
    {
        protected override DependencyObject CreateShell()
        {
            return UnityHelper.GetInstance<Wizard.Wpf.Program.Application.Login.View.LoginWindowView>();
        }

        protected override void InitializeShell()
        {
            App.Current.MainWindow = (Window)this.Shell;
            App.Current.MainWindow.Show();
        }

        protected override void ConfigureModuleCatalog()
        {
            //ModuleCatalog moduleCatalog = (ModuleCatalog)this.ModuleCatalog;
            //moduleCatalog.AddModule(typeof(Wizard.Wpf.Program.Application.Main.MainModule));
        }

        protected override void ConfigureContainer()
        {
            base.ConfigureContainer();
        }
    }
}
