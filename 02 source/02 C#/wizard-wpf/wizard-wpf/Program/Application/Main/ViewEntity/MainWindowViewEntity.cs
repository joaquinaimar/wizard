using System;
using Wizard.Wpf.Program.Basic.Framework;

namespace Wizard.Wpf.Program.Application.Main.ViewEntity
{
    public class MainWindowViewEntity : ViewEntityBase
    {
        private DateTime currentTime;
        public DateTime CurrentTime
        {
            get
            {
                return currentTime;
            }
            set
            {
                currentTime = value;
                RaisePropertyChanged("CurrentTime");
            }
        }

    }
}
