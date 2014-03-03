using System;
using System.Reflection;
using System.Windows;

namespace Wizard.Wpf.Program.Helper
{
    public static class CommonHelper
    {
        public static void SetPropertyValue(object obj, string name, object value)
        {
            Type type = obj.GetType();
            PropertyInfo propertyInfo = type.GetProperty(name);
            if (null != propertyInfo && propertyInfo.CanWrite)
                propertyInfo.SetValue(obj, value, null);
        }

        public static object GetPropertyValue(object obj, string name)
        {
            Type type = obj.GetType();
            PropertyInfo propertyInfo = type.GetProperty(name);
            if (null != propertyInfo && propertyInfo.CanWrite)
                return propertyInfo.GetValue(obj, null);
            return null;
        }

        public static Window GetWindow(FrameworkElement obj)
        {
            if (null == obj.Parent)
                if (obj is Window)
                    return obj as Window;
                else
                    return null;
            else
                return GetWindow(obj.Parent as FrameworkElement);

        }
    }
}
