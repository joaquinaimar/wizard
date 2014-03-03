using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Practices.ServiceLocation;

namespace Wizard.Wpf.Program.Helper
{
    public static class UnityHelper
    {
        public static T GetInstance<T>()
        {
            return ServiceLocator.Current.GetInstance<T>();
        }

        public static object GetInstance(Type type)
        {
            return ServiceLocator.Current.GetInstance(type);
        }

        public static object GetInstance(string type)
        {
            return GetInstance(Type.GetType(type));
        }

    }
}
