using System;
using System.Windows.Controls;
using System.Windows.Navigation;
using System.Windows.Threading;
using Microsoft.Practices.Prism.Commands;
using Wizard.Wpf.Program.Application.Main.View;
using Wizard.Wpf.Program.Application.Main.ViewEntity;
using Wizard.Wpf.Program.Basic.Framework;
using Wizard.Wpf.Program.Basic.UI.Tab;
using Wizard.Wpf.Program.Helper;

namespace Wizard.Wpf.Program.Application.Main.ViewModel
{
    public class MainWindowViewModel : ViewModelBase<MainWindowViewEntity>
    {

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
            GetTime();
        }
        #endregion

        #region 标签
        public DelegateCommand<string> MnuTabCommand
        {
            get
            {
                return new DelegateCommand<string>(this.MnuTabExecute);
            }
        }

        private void MnuTabExecute(string arg)
        {

            ItemCollection Items = this.GetView<MainWindowView>().MainTab.Items;

            Tab tabItem = UnityHelper.GetInstance<Tab>();
            Page page = UnityHelper.GetInstance(arg) as Page;

            for (int i = 0; i < Items.Count; i++)
            {
                tabItem =Items.GetItemAt(i) as Tab;
                Frame content = tabItem.Content as Frame;
                if (content.Content.GetType().FullName.Equals(arg))
                {
                    tabItem.Focus();
                    return;
                }
            }

            tabItem.Title = page.Title;
            tabItem.Content = new Frame() { NavigationUIVisibility = NavigationUIVisibility.Hidden, Content = page };
            this.GetView<MainWindowView>().MainTab.Items.Add(tabItem);
            tabItem.Focus();

        }
        #endregion

        #region 关闭
        public DelegateCommand MnuExitCommand
        {
            get
            {
                return new DelegateCommand(this.MnuExitExecute);
            }
        }

        private void MnuExitExecute()
        {
            this.GetView<MainWindowView>().Close();
        }
        #endregion

        #region 私有方法
        private void GetTime()
        {
            DispatcherTimer timer = new DispatcherTimer();
            timer.Tick += new EventHandler((sender, args) =>
            {
                this.ViewEntity.CurrentTime = DateTime.Now;
            });
            timer.Start();
        }
        #endregion

    }
}
