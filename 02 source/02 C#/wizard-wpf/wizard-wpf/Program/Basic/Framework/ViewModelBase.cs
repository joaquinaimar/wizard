using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Practices.Prism.Events;
using Microsoft.Practices.Prism.Regions;
using Microsoft.Practices.ServiceLocation;
using Microsoft.Practices.Unity;
using Wizard.Wpf.Program.Basic.Framework.Event;

namespace Wizard.Wpf.Program.Basic.Framework
{
    public abstract class ViewModelBase<E>
        where E : ViewEntityBase, new()
    {
        [Dependency]
        public IUnityContainer Container { get; set; }

        [Dependency]
        public IServiceLocator ServiceLocator { get; set; }

        [Dependency]
        public IRegionManager RegionManager { get; set; }

        [Dependency]
        public IEventAggregator EventAggregator { get; set; }

        [Dependency]
        public E ViewEntity { get; set; }

        public object View { private get; set; }

        public V GetView<V>()
        {
            return (V)this.View;
        }

    }
}
