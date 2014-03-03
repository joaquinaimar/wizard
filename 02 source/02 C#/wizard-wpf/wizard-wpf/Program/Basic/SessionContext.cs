using System.Collections.Generic;

namespace Wizard.Wpf.Program.Basic
{
    public static class SessionContext
    {
        private static Dictionary<string, object> sessionContext = new Dictionary<string, object>();

        public static void SetAttribute(string key, object content)
        {
            if (sessionContext.ContainsKey(key))
                sessionContext.Remove(key);
            sessionContext.Add(key, content);
        }

        public static object GetAttribute(string key)
        {
            if (sessionContext.ContainsKey(key))
                return sessionContext[key];
            return null;
        }

        public static X GetAttribute<X>(string key) where X : class
        {
            return GetAttribute(key) as X;
        }

    }
}
